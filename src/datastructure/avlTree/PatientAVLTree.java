package datastructure.avlTree;

import model.Patient;

/**
 * Cay tim kiem nhi phan tu can bang (AVL Tree) de luu tru va tra cuu benh nhan.
 *
 * Member 1 se implement class nay.
 */
public class PatientAVLTree implements IPatientAVLTree {
    private PatientNode root;

    public PatientAVLTree() {
        this.root = null;
    }

    @Override
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private PatientNode insertRec(PatientNode node, Patient patient) {
        if (node == null) {
            return new PatientNode(patient);
        }

        int compare = patient.getPatientId().compareTo(node.getPatient().getPatientId());
        if (compare < 0) {
            node.setLeft(insertRec(node.getLeft(), patient));
        } else if (compare > 0) {
            node.setRight(insertRec(node.getRight(), patient));
        } else {
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private int height(PatientNode node) {
        return node == null ? 0 : node.getHeight();
    }

    private void updateHeight(PatientNode node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    private int getBalance(PatientNode node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private PatientNode balance(PatientNode node) {
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public Patient search(String patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(PatientNode node, String patientId) {
        if (node == null) {
            return null;
        }
        int compare = patientId.compareTo(node.getPatient().getPatientId());
        if (compare == 0) {
            return node.getPatient();
        }
        if (compare < 0) {
            return searchRec(node.getLeft(), patientId);
        }
        return searchRec(node.getRight(), patientId);
    }

    @Override
    public boolean delete(String patientId) {
        DeleteResult result = deleteRec(root, patientId);
        root = result.node;
        return result.deleted;
    }

    private static class DeleteResult {
        private final PatientNode node;
        private final boolean deleted;

        private DeleteResult(PatientNode node, boolean deleted) {
            this.node = node;
            this.deleted = deleted;
        }
    }

    private DeleteResult deleteRec(PatientNode node, String patientId) {
        if (node == null) {
            return new DeleteResult(null, false);
        }

        int compare = patientId.compareTo(node.getPatient().getPatientId());
        if (compare < 0) {
            DeleteResult leftResult = deleteRec(node.getLeft(), patientId);
            node.setLeft(leftResult.node);
            if (!leftResult.deleted) {
                return leftResult;
            }
        } else if (compare > 0) {
            DeleteResult rightResult = deleteRec(node.getRight(), patientId);
            node.setRight(rightResult.node);
            if (!rightResult.deleted) {
                return rightResult;
            }
        } else {
            PatientNode replacement;
            if (node.getLeft() == null) {
                replacement = node.getRight();
                return new DeleteResult(replacement, true);
            } else if (node.getRight() == null) {
                replacement = node.getLeft();
                return new DeleteResult(replacement, true);
            }

            PatientNode successor = findMin(node.getRight());
            node.setPatient(successor.getPatient());
            DeleteResult rightResult = deleteRec(node.getRight(), successor.getPatient().getPatientId());
            node.setRight(rightResult.node);
            if (!rightResult.deleted) {
                return rightResult;
            }
        }

        updateHeight(node);
        return new DeleteResult(balance(node), true);
    }

    private PatientNode findMin(PatientNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private PatientNode rightRotate(PatientNode y) {
        PatientNode x = y.getLeft();
        PatientNode t2 = x.getRight();

        x.setRight(y);
        y.setLeft(t2);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private PatientNode leftRotate(PatientNode x) {
        PatientNode y = x.getRight();
        PatientNode t2 = y.getLeft();

        y.setLeft(x);
        x.setRight(t2);

        updateHeight(x);
        updateHeight(y);

        return y;
    }
}
